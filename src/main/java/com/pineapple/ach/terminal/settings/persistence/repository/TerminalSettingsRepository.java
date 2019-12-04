package com.pineapple.ach.terminal.settings.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettings;

@Repository
public interface TerminalSettingsRepository extends JpaRepository<TerminalSettings, Long> {

}
