package jlox;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

class LoxInstance {
    private LoxClass kclass;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass kclass){
        this.kclass = kclass;
    }
    
    Object get(Token name){
        if(fields.containsKey(name.lexeme)){
            return fields.get(name.lexeme);
        }

        LoxFunction method = kclass.findMethod(name.lexeme);
        if(method != null) return method.bind(this);

        throw new RuntimeError(name,
        "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value){
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString(){
        return kclass.name + " instance";
    }
}
