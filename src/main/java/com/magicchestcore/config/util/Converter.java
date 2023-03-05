package com.magicchestcore.config.util;

import com.magicchestcore.dto.*;
import com.magicchestcore.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


// вынести в отдельный класс все м-ды// convert(class+static method or @component+import)
@Component
public class Converter {
    private final ModelMapper modelMapper;
    public Converter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Product convertToProductType(ProductDTO productDTO){
        ProductType productType = productDTO.getProductType();
        Product product=null;
        switch (productType){
            case DRESS:
               product = convertToDress(productDTO);
                break;
            case BAG:
                product = convertToBag(productDTO);
                break;
            case SHOES:
                product = convertToShoes(productDTO);
                break;
        }
        return product;
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


    public Product convertToProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }
    public ProductDTO convertToProductDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }


    public Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
    public PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }


    public Person convertToPerson(PersonAuthDTO personAuthDTO){
        return modelMapper.map(personAuthDTO, Person.class);
    }
    public PersonAuthDTO convertToPersonAuthDTO(Person person){
        return modelMapper.map(person, PersonAuthDTO.class);
    }


    public Order convertToOrder(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }
    public OrderDTO convertToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }


    public Dress convertToDress(DressDTO dressDTO){
        return modelMapper.map(dressDTO, Dress.class);
    }
    public DressDTO convertToDressDTO(Dress dress){
        return modelMapper.map(dress, DressDTO.class);
    }


    public Shoes convertToShoes(ShoesDTO shoesDTO){
        return modelMapper.map(shoesDTO, Shoes.class);
    }
    public ShoesDTO convertToShoesDTO(Shoes shoes){
        return modelMapper.map(shoes, ShoesDTO.class);
    }


    public Bag convertToBag(BagDTO bagDTO){
        return modelMapper.map(bagDTO, Bag.class);
    }
    public BagDTO convertToBagDTO(Bag bag){
        return modelMapper.map(bag, BagDTO.class);
    }


    public DressModel convertToDressModel(DressModelDTO dressModelDTO){
        return modelMapper.map(dressModelDTO, DressModel.class);
    }
    public DressModelDTO convertToDressModelDTO(DressModel dressModel){
        return modelMapper.map(dressModel, DressModelDTO.class);
    }



    public ShoesModel convertToShoesModel(ShoesModelDTO shoesModelDTO){
        return modelMapper.map(shoesModelDTO, ShoesModel.class);
    }
    public ShoesModelDTO convertToShoesModelDTO(ShoesModel shoesModel){
        return modelMapper.map(shoesModel, ShoesModelDTO.class);
    }


    public BagModel convertToBagModel(BagModelDTO bagModelDTO){
        return modelMapper.map(bagModelDTO, BagModel.class);
    }
    public BagModelDTO convertToBagModelDTO(BagModel bagModel){
        return modelMapper.map(bagModel, BagModelDTO.class);
    }



    public ShoesSize convertToShoesSize(ShoesSizeDTO shoesSizeDTO){
        return modelMapper.map(shoesSizeDTO, ShoesSize.class);
    }
    public ShoesSizeDTO convertToShoesSizeDTO(ShoesSize shoesSize){
        return modelMapper.map(shoesSize, ShoesSizeDTO.class);
    }


    public BagSize convertToBagSize(BagSizeDTO bagSizeDTO){
        return modelMapper.map(bagSizeDTO, BagSize.class);
    }
    public BagSizeDTO convertToBagSizeDTO(BagSize bagSize){
        return modelMapper.map(bagSize, BagSizeDTO.class);
    }

    public DressSize convertToDressSize(DressSizeDTO dressSizeDTO){
        return modelMapper.map(dressSizeDTO,DressSize.class);
    }
    public DressSizeDTO convertToDressSizeDTO(DressSize dressSize){
        return modelMapper.map(dressSize, DressSizeDTO.class);
    }



    public Color convertToColor(ColorDTO colorDTO){
        return modelMapper.map(colorDTO, Color.class);
    }
    public ColorDTO convertToColorDTO(Color color){
        return modelMapper.map(color, ColorDTO.class);
    }
}
