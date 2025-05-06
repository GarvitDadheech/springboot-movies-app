package com.movieapp.service;

import com.movieapp.model.Director;
import com.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Director> findAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Director> findDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    @Transactional
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    @Transactional
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
} 