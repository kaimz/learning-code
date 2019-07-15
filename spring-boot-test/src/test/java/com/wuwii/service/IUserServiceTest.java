package com.wuwii.service;

import com.wuwii.model.User;
import com.wuwii.repository.IUserRepository;
import com.wuwii.service.impl.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * Created by KronChan on 2018/4/26 15:41.
 */
public class IUserServiceTest {
    private IUserService userService;

    //@Mock
    private IUserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        // 对所有注解了@Mock的对象进行模拟
        // MockitoAnnotations.initMocks(this);
        // 不使用注解，可以对单个对象进行 mock
        userRepository = Mockito.mock(IUserRepository.class);
        // 构造被测试对象
        userService = new UserServiceImpl(userRepository);
        // 打桩，构建当 userRepository的 getOne 函数执行参数为 1的时候，设置返回的结果 User
        Mockito.when(userRepository.getOne(1L)).thenReturn(new User(1L, "kronchan"));
        // 打桩，构建当 userRepository的 getOne 函数执行参数为 2的时候，设置返回的结果 null
        Mockito.when(userRepository.getOne(2L)).thenReturn(null);
        // 打桩，构建当 userRepository的 getOne 函数执行参数为 3的时候，设置结果抛出异常
        Mockito.when(userRepository.getOne(3L)).thenThrow(new IllegalArgumentException("The id is not support"));
        // 打桩，当 userRepository.updateUser 执行任何User类型的参数，返回的结果都是true
        Mockito.when(userRepository.save(mockUser())).thenReturn(mockUser());
    }

    private User mockUser() {
        User user = new User();
        user.setUsername("kronchan");
        user.setPassword("123456");
        return user;
    }

    @Test
    public void testUpdateUsernameSuccess() {
        long userId = 1L;
        String newUsername = "new kronchan";
        // 测试某个 service 的方法
        boolean updated = userService.updateUsername(userId, newUsername);
        // 检查结果
        Assert.assertThat(updated, Matchers.is(true));
        // Verifies certain behavior <b>happened once</b>.
        // mock对象一旦创建，就会自动记录自己的交互行为。通过verify(mock).someMethod()方法，来验证方法是否被调用。
        // 验证调用上面的service 方法后是否 userRepository.getOne(1L) 调用过，
        Mockito.verify(userRepository).getOne(userId);
        // 有条件可以测试下没有被调用过的方法：
        //   Mockito.verify(userRepository).deleteById(userId);
        //   则会测试失败：
        //    Wanted but not invoked:
        //      userRepository.deleteById(1L);
        //    However, there were exactly 2 interactions with this mock:
        //      userRepository.getOne(1L);
        //      userRepository.updateUser(
        //         User(id=1, username=new kronchan, password=null, createDate=null)
        //      );

        //  updateUsername 函数中我们调用了已经打桩了的其他的函数，现在我们来验证进入其他函数中的参数
        // 构造参数捕获器，用于捕获方法参数进行验证
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        // 验证updateUser方法是否被调用过，并且捕获入参
        Mockito.verify(userRepository).save(userCaptor.capture());
        // 获取参数 updatedUser
        User updatedUser = userCaptor.getValue();
        // 验证入参是否是预期的
        Assert.assertThat(updatedUser.getUsername(), Matchers.is(newUsername));
        //保证这个测试用例中所有被Mock的对象的相关方法都已经被Verify过了
        Mockito.verifyNoMoreInteractions(userRepository);
        // 如果有一个交互，但是我们没有verify ，则会报错，
        //      org.mockito.exceptions.verification.NoInteractionsWanted:
        //      No interactions wanted here:
        //      -> at com.wuwii.service.IUserServiceTest.testUpdateUsernameSuccess(IUserServiceTest.java:74)
        //      But found this interaction on mock 'iUserRepository':
        //      -> at com.wuwii.service.impl.UserServiceImpl.findOne(UserServiceImpl.java:21)
        //      ***
    }

    @Test
    public void testUpdateUsernameFailed() {
        long userId = 2L;
        String newUsername = "new kronchan";
        // 没有经过 mock 的 updateUser 方法，它返回的是 false
        boolean updated = userService.updateUsername(userId, newUsername);
        Assert.assertThat(updated, Matchers.not(true));
        //验证userRepository的getOne(2L)这个方法是否被调用过，（这个是被测试过的，此步骤通过）
        Mockito.verify(userRepository).getOne(2L);
        // 验证 userRepository 的 updateUser(null)这个方法是否被调用过，（这个没有被测试过，此步骤不通过）
        //Mockito.verify(userRepository).updateUser(null);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

}