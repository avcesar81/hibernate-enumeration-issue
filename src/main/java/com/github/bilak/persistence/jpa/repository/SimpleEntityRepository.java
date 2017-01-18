package com.github.bilak.persistence.jpa.repository;

import com.github.bilak.persistence.jpa.model.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lvasek on 18/01/2017.
 */
@Repository
public interface SimpleEntityRepository extends JpaRepository<SimpleEntity, String>, JpaSpecificationExecutor<SimpleEntity> {
}
