package com.ranyikang.ssh.entity;

import lombok.*;

import java.io.Serializable;

/**
 * CLASS_NAME: ItemStyle.java <br/>
 * Description: SunburstDrink 子类对象 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 08
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemStyle  implements Serializable {

    private static final long serialVersionUID = 459779685225165766L;

    private String color;
}
