package com.fiap.challengeJava.repository;
import com.fiap.challengeJava.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
