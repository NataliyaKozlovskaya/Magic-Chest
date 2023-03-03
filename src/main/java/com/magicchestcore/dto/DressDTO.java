package com.magicchestcore.dto;

import com.magicchestcore.models.DressModel;
import com.magicchestcore.models.DressSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DressDTO {

    private DressModel dressModel;

    private DressSize dressSize;
}
