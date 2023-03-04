package com.magicchestcore.config.util;

import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.Bag;
import com.magicchestcore.models.Dress;
import com.magicchestcore.models.Product;
import com.magicchestcore.models.Shoes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static com.magicchestcore.config.util.ProductType.*;

@Component
public class Convert {
    // вынести в отдельный класс все м-ды// convert(class+static method or @component+import)
    public  void convert1(ProductDTO productDTO){

        ProductType productType = productDTO.getProductType();
        //Product product1 = null;
        switch (productType){
            case DRESS:
                convertToDress(productDTO);
                break;
            case BAG:
                convertToBag(productDTO);
                break;
            case SHOES:
                convertToShoes(productDTO);
                break;
        }

}
    private final ModelMapper modelMapper;

    public Convert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Dress convertToDress(ProductDTO productDTO){
        return modelMapper.map(productDTO, Dress.class);
    }





    public Bag convertToBag(ProductDTO productDTO){
        return modelMapper.map(productDTO, Bag.class);
    }

    public Shoes convertToShoes(ProductDTO productDTO){
        return modelMapper.map(productDTO, Shoes.class);
    }
}
