package com.example.inventory;

import com.example.inventory.dao.entity.AddressEntity;
import com.example.inventory.dao.entity.CatalogEntity;
import com.example.inventory.dao.entity.InventoryEntity;
import com.example.inventory.dao.entity.InventoryEntity.InventoryStatus;
import com.example.inventory.dao.entity.ProductEntity;
import com.example.inventory.dao.entity.ShipmentEntity;
import com.example.inventory.dao.entity.ShipmentEntity.ShipmentStatus;
import com.example.inventory.dao.entity.WarehouseEntity;
import com.example.inventory.dao.repository.AddressRepository;
import com.example.inventory.dao.repository.CatalogRepository;
import com.example.inventory.dao.repository.InventoryRepository;
import com.example.inventory.dao.repository.ProductRepository;
import com.example.inventory.dao.repository.ShipmentRepository;
import com.example.inventory.dao.repository.WarehouseRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer {

  private final ProductRepository productRepository;
  private final ShipmentRepository shipmentRepository;
  private final WarehouseRepository warehouseRepository;
  private final AddressRepository addressRepository;
  private final CatalogRepository catalogRepository;
  private final InventoryRepository inventoryRepository;

  public void populate() throws Exception {

    productRepository.deleteAll();
    shipmentRepository.deleteAll();
    warehouseRepository.deleteAll();
    addressRepository.deleteAll();
    catalogRepository.deleteAll();
    inventoryRepository.deleteAll();

    WarehouseEntity warehouse = new WarehouseEntity("Pivotal SF");

    List<ProductEntity> products = new ArrayList<>(Arrays.asList(

        new ProductEntity("Best. Cloud. Ever. (T-Shirt, Men's Large)", "SKU-24642",
            "<p>Do you love your cloud platform? " +
                "Do you push code continuously into production on a daily basis? " +
                "Are you living the cloud native microservice dream? Then rain or shine, this T-Shirt is for you. "
                +
                "Show the world you're a stylish cloud platform architect with this cute yet casual tee. "
                +
                "<br /><br />&nbsp; <strong>Cloud Native Tee Collection</strong><br />" +
                "&nbsp; 110% cloud stuff, 5% spandex<br />&nbsp; Rain wash only<br />&nbsp; " +
                "Four nines of <em>stylability</em></p>", 21.99),

        new ProductEntity("Like a BOSH (T-Shirt, Women's Medium)", "SKU-34563",
            "<p>The BOSH Outer Shell (<strong>BOSH</strong>) " +
                "is an elegant release engineering tool for a more virtualized cloud-native age. " +
                "The feeling of spinning up a highly available distributed system of VMs is second only to the "
                +
                "feeling of frequently pushing code to production. Show the cloud <em>who's BOSH</em> with "
                +
                "this stylish cloud native ops tee.<br /><br />&nbsp; <strong>Cloud Native Tee Collection</strong><br />&nbsp; "
                +
                "99% YAML, 11% CLI<br />&nbsp; BOSH CCK <span style='text-decoration: underline;'><em>recommended</em></span><br />&nbsp; "
                +
                "4 nines of <em>re-washability</em></p>", 14.99),

        new ProductEntity("We're gonna need a bigger VM (T-Shirt, Women's Small)", "SKU-12464",
            "<i>\"Mr. Vaughn, what we are dealing with here is " +
                "a perfect engine, an eating machine. It's really a miracle of evolution. All this machine does is swim and eat and make "
                +
                "little containers, and that's all.\"</i>", 13.99),

        new ProductEntity("cf push awesome (Hoodie, Men's Medium)", "SKU-64233",
            "<p>One of the great natives of the cloud once said \"<em>" +
                "Production is the happiest place on earth for us - it's better than Disneyland</em>\". "
                +
                "With this stylish Cloud Foundry hoodie you can push code to the cloud all day while staying "
                +
                "comfortable and casual. <br /><br />&nbsp; <strong>Cloud Native PaaS Collection</strong><br />"
                +
                "&nbsp; 10% cloud stuff, 90% platform nylon<br />&nbsp; Cloud wash safe<br />" +
                "&nbsp; Five nines of <em>comfortability</em></p>", 21.99)));

    productRepository.saveAll(products);

    CatalogEntity catalog = new CatalogEntity("Fall Catalog", 0L);
    catalog.getProducts().addAll(products);
    catalogRepository.save(catalog);

    AddressEntity warehouseAddress = new AddressEntity("875 Howard St", null,
        "CA", "San Francisco", "United States", 94103);

    AddressEntity shipToAddress = new AddressEntity("1600 Amphitheatre Parkway", null,
        "CA", "Mountain View", "United States", 94043);

    // Save the addresses
    addressRepository.saveAll(Arrays.asList(warehouseAddress, shipToAddress));
    warehouse.setAddressEntity(warehouseAddress);
    warehouse = warehouseRepository.save(warehouse);
    WarehouseEntity finalWarehouse = warehouse;

    // Create a new set of inventories with a randomized inventory number
    Set<InventoryEntity> inventories = products.stream()
        .map(a -> new InventoryEntity(IntStream.range(0, 9)
            .mapToObj(x -> Integer.toString(new Random().nextInt(9)))
            .collect(Collectors.joining("")), a, finalWarehouse, InventoryStatus.IN_STOCK))
        .collect(Collectors.toSet());

    inventoryRepository.saveAll(inventories);

    // Generate 10 extra inventory for each product
    for (int i = 0; i < 10; i++) {
      inventoryRepository.saveAll(products.stream()
          .map(a -> new InventoryEntity(IntStream.range(0, 9)
              .mapToObj(x -> Integer.toString(new Random().nextInt(9)))
              .collect(Collectors.joining("")), a, finalWarehouse, InventoryStatus.IN_STOCK))
          .collect(Collectors.toSet()));
    }

    ShipmentEntity shipment = new ShipmentEntity(inventories, shipToAddress,
        warehouse, ShipmentStatus.SHIPPED);

    shipmentRepository.save(shipment);
  }

}
