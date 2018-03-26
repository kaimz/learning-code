package com.wuwii.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QJob is a Querydsl query type for Job
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJob extends EntityPathBase<Job> {

    private static final long serialVersionUID = 1065600180L;

    public static final QJob job = new QJob("job");

    public final SetPath<Employee, QEmployee> employees = this.<Employee, QEmployee>createSet("employees", Employee.class, QEmployee.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QJob(String variable) {
        super(Job.class, forVariable(variable));
    }

    public QJob(Path<? extends Job> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJob(PathMetadata metadata) {
        super(Job.class, metadata);
    }

}

