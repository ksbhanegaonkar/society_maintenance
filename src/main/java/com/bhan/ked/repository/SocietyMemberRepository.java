package com.bhan.ked.repository;

import com.bhan.ked.entity.SocietyMemberEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.inject.Singleton;

@Repository
public interface SocietyMemberRepository extends CrudRepository<SocietyMemberEntity, Integer> {
}
