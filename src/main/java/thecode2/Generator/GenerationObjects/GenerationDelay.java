package thecode2.Generator.GenerationObjects;

import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;

public class GenerationDelay extends GenerationObject {

    GenerationObject o;

    public GenerationDelay(GenerationObject o) {
        super(0,0,0);
        this.o=o;
    }

    @Override
    public void generate(GenerationTarget target){
        Generator.Queue(o);
    }
}
