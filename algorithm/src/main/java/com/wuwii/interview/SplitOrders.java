package com.wuwii.interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拆单问题
 */
public class SplitOrders {

    public class Item {
        /**
         * 卖家用户id
         */
        long sellerId;
        /**
         * 商品价格，单位分
         */
        long price;
    }

    public class Order {
        /**
         * 该订单对应的商品
         */
        List<Item> orderItems;
        /**
         * 该订单金额，单位分
         */
        long totalPrice;
        /**
         * 该订单对应的卖家userId
         */
        long sellerId;
    }

    /**
     * 根据购物车的商品，生成相应的交易订单，根据如下规则
     * 1.每笔交易订单可以有多个商品
     * 2.每笔交易订单的商品只能是同一个卖家
     * 3.每笔交易商品的总价格不能超过1000元
     * 4.生成的交易订单数量最小
     *
     * @param items：购物车所有商品
     */
    public List<Order> packageItemsToOrders(List<Item> items) {
        // 按照卖家分组
        Map<Long, List<Item>> itemMapBySeller = items.stream()
                .collect(Collectors.groupingBy(i -> {
                    return i.sellerId;
                }, Collectors.toList()));

        // 拆单的合并结果
        List<Order> result = new LinkedList<>();
        itemMapBySeller.entrySet().stream()
                .map(this::splitItems)
                .forEach(result::addAll);
        return result;
    }

    /**
     * 拆单
     * <p>
     * 对单个卖家进行拆单；
     *
     * @param itemsEntry 这个卖家和商品关系
     * @return 对这个卖家的拆单结果
     */
    private List<Order> splitItems(Map.Entry<Long, List<Item>> itemsEntry) {
        // 1. 订单数量最少的情况，保证金额不大于1000，尽量合并价格接近1000
        //  必要条件取出一个最大的订单，一直拼单，直到最后最小的不能拼上去，表示这一单拆成功了。
        // 对 items 进行排序
        // 先取一个最大，找一个能够拼最接近 1000 的，
        //     1. 如果找不到一个能够拼单成功的 则直接单独一个订单
        //     2. 如果找到了一个成功的，然后还要继续找大的直到拼单失败，
        //            则表示能够拼单成功，然后还要重新遍历一遍，从小最小的开始还能不能继续拼单，
        //               继续下去直到最小的一单也不能拼表示拼单完成。
        List<Order> result = new LinkedList<>();

        long sellerId = itemsEntry.getKey();
        List<Item> items = itemsEntry.getValue();
        items.sort((o1, o2) -> Long.compare(o1.price, o2.price));

        int initSize = items.size();
        for (int i = 0; i < initSize; i++) {
            int size = items.size();
            if (size == 0) {
                break;
            }
            if (size == 1) {
                Order order = new Order();
                Item item = items.get(0);
                order.sellerId = sellerId;
                order.totalPrice = item.price;
                order.orderItems = Collections.singletonList(item);
                result.add(order);
                return result;
            }
            int lastIndex = size - 1;
            Item maxItem = items.get(lastIndex);
            long maxItemPrice = maxItem.price;

            items.remove(lastIndex);
            List<Item> splitOrders = new LinkedList<>();
            splitOrders.add(maxItem);
            splitItems(items, maxItemPrice, sellerId, splitOrders, result);
        }
        return result;
    }

    /**
     * 拆单
     *
     * @param items        单数
     * @param currentPrice 当前拆单的总价
     * @param sellerId     卖家
     * @param splitItems   拆单的集合
     * @param result       结果集
     */
    private void splitItems(List<Item> items, long currentPrice, long sellerId, List<Item> splitItems, List<Order> result) {
        int size = items.size();
        if (size == 0) {
            return;
        }
        Item preItem = null;
        int i = 0;
        for (; i < size; i++) {
            Item item = items.get(i);
            long tmpPrice = item.price;
            if (tmpPrice + currentPrice > 1000) {
                // 最小的单也不能拼进，表示拆单完成
                if (preItem == null) {
                    Order order = new Order();
                    order.sellerId = sellerId;
                    order.totalPrice = currentPrice;
                    order.orderItems = splitItems;
                    result.add(order);
                    return;
                }
                break;
            }
            preItem = item;
        }
        // 准备下轮轮询
        currentPrice += preItem.price;
        splitItems.add(preItem);
        items.remove(i - 1);
        if (items.size() == 0) {
            Order order = new Order();
            order.sellerId = sellerId;
            order.totalPrice = currentPrice;
            order.orderItems = splitItems;
            result.add(order);
            return;
        }
        splitItems(items, currentPrice, sellerId, splitItems, result);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
// TODO Auto-generated method stub
        SplitOrders splitOrders = new SplitOrders();
        List<Item> items = new ArrayList<>(20);
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 20; i++) {
                int price = new Random().nextInt(1000);
                Item item = splitOrders.new Item();
                item.price = price;
                item.sellerId = j;
                items.add(item);
            }
        }
        splitOrders.packageItemsToOrders(items);
    }
}