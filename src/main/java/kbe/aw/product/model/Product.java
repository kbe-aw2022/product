package kbe.aw.product.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   private String name;

   private String description;

   @OneToMany(fetch = FetchType.EAGER)
   private List<HardwareComponent> hardwareComponents;
}
