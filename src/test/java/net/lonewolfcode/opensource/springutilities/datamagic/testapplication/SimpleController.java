package net.lonewolfcode.opensource.springutilities.datamagic.testapplication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SimpleController
{
    @PostMapping
    public void doPost(@RequestBody String someValue)
    {

    }

    @PostMapping("/secondary")
    public void doSecondaryPost(@RequestBody String someValue)
    {

    }
}
