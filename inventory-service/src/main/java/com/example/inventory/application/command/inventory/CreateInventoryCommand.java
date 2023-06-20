package com.example.inventory.application.command.inventory;

import com.example.common.command.Command;
import com.example.inventory.application.dto.InventoryItemDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CreateInventoryCommand implements Command<Void> {

  @NotEmpty
  private List<@Valid InventoryItemDTO> inventoryItems;

}
