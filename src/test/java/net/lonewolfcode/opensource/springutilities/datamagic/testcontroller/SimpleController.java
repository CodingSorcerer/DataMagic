package net.lonewolfcode.opensource.springutilities.datamagic.testcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController
{
    @PostMapping
    public void doPost()
    {

    }

    @PostMapping("/secondary")
    public void doSecondaryPost()
    {

    }
}
