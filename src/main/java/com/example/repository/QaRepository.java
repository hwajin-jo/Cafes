package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.QnaAnswer;

public interface QaRepository extends JpaRepository<QnaAnswer, Integer> {

}
