package com.webther.pronun.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.webther.pronun.data.entity.PuzzleEntity;

/**
 * Repository for accessing puzzles
 * 
 * @author Barnabas
 */
@Transactional	
public interface PuzzleRepository extends JpaRepository<PuzzleEntity, Long>{
    
    
}
