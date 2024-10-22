package com.bhan.ked.repository;

import com.bhan.ked.entity.UserDetailEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.inject.Singleton;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetailEntity, Integer> {
    Optional<UserDetailEntity> getByMobileNo(String mobileNo);
}
