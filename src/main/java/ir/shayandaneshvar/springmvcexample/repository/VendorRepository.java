package ir.shayandaneshvar.springmvcexample.repository;

import ir.shayandaneshvar.springmvcexample.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
