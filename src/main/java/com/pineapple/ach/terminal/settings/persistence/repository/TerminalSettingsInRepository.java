package com.pineapple.ach.terminal.settings.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettingsIn;

@Repository
public interface TerminalSettingsInRepository extends JpaRepository<TerminalSettingsIn, Long> {

    List<TerminalSettingsIn> findByActive(Boolean active);

    Long countByActive(Boolean active);

}
