package com.elpsykongrood.projects;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("""
            select p.slug, p.title, p.description, p.kind,
                   p.websiteUrl, p.portfolioUrl, p.previewImage
            from Project p
            order by p.displayOrder, p.slug
            """)
    List<ProjectPayloads.Item> findProjects();
}
