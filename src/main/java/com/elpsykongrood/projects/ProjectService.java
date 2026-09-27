package com.elpsykongrood.projects;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projects;

    public List<ProjectPayloads.Item> findProjects() {
        return projects.findProjects();
    }
}
