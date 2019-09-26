package ir.shayandaneshvar.springmvcexample.repository;

import ir.shayandaneshvar.springmvcexample.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
