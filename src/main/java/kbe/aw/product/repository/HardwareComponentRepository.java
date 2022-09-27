package kbe.aw.product.repository;

import org.springframework.data.repository.CrudRepository;

import kbe.aw.product.model.HardwareComponent;

public interface HardwareComponentRepository extends CrudRepository<HardwareComponent, Integer>
{
}
