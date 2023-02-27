package com.kokoo.aurora.service;

import com.kokoo.aurora.domain.Split;
import com.kokoo.aurora.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SplitService {

    private final ConnectionRepository connectionRepository;
    private final DataSource dataSource;

    @Transactional(readOnly = true)
    public List<Split> read() {
        try (Connection connection = dataSource.getConnection()) {
            String connectedUrl = connection.getMetaData().getURL();
            log.info("current connected url :: {}", connectedUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connectionRepository.findAll();
    }

    @Transactional
    public Long write() {
        try (Connection connection = dataSource.getConnection()) {
            String connectedUrl = connection.getMetaData().getURL();
            log.info("current connected url :: {}", connectedUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Split split = Split.builder().build();
        Split savedSplit = connectionRepository.save(split);

        return savedSplit.getId();
    }
}
