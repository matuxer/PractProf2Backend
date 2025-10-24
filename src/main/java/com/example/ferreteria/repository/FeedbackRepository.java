package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ferreteria.model.FeedbackModel;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
}

