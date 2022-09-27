package kbe.aw.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class HardwareComponent
{
   @Id
   private Integer id;

   private String name;

   private String vendor;

   @NotNull
   private double price;

   private String description;

   private String location;

   private String manufacture;

   private String productGroup;

   private int weightInGramm;

   private Status status;

   private String eanNumber;

}
