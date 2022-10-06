package com.dxc.repository;
import org.springframework.data.repository.CrudRepository;

import com.dxc.model.Trainer;
public interface UserRepository extends CrudRepository<Trainer, Integer> {
    Trainer findByUsername(String username);
}