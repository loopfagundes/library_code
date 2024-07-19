package br.dev.codex.json.filesJson;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Description {
    private String Sendkeys;

    public Description() {

    }

    public Description(String Sendkeys) {
        this.Sendkeys = Sendkeys;
    }
}