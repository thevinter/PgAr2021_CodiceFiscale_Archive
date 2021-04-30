/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.xml.engine.annotations.EngineMethod;
import ttt.utils.xml.engine.annotations.Tag;
import ttt.utils.xml.engine.enums.MethodType;

/**
 *Rappresenta l'elemento "codici"
 * @author TTT
 */
@Element(Name = "codici", CanHaveValue = false)
public class Codici extends XMLElement {

    private Integer size = 0;

    public Codici() {
        super("codici");
    }

    @EngineMethod(MethodType = MethodType.SET)
    @Tag(Name = "size")
    public void setSize(String value) {
        size = Integer.parseInt(value);
    }

    @EngineMethod(MethodType = MethodType.GET)
    @Tag(Name = "size", ValueType = Integer.class)
    public Integer setSize() {
        return size;
    }

}