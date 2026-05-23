package com.zp.dao;

import com.zp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author zhengpanone
 * @since 2022-09-19
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
