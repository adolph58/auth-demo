package com.example.demo.auth;

/**
 * @author Arte
 * @create 2023/5/10.
 */
public interface IAccount {
    <T> T getUnionId();

    String getName();
}
