package com.wuwii.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEmployeeDetail is a Querydsl query type for EmployeeDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeeDetail extends EntityPathBase<EmployeeDetail> {

    private static final long serialVersionUID = -1230694584L;

    public static final QEmployeeDetail employeeDetail = new QEmployeeDetail("employeeDetail");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public QEmployeeDetail(String variable) {
        super(EmployeeDetail.class, forVariable(variable));
    }

    public QEmployeeDetail(Path<? extends EmployeeDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeeDetail(PathMetadata metadata) {
        super(EmployeeDetail.class, metadata);
    }

}

