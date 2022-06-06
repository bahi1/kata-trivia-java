package trivia;

import java.util.ArrayList;
import java.util.List;

public class PopQuestion implements Question{
    private final List<String> questions = new ArrayList<>();
    @Override
    public void add(String question) {
        questions.add(question);
    }

    @Override
    public String remove(int question) {
        return questions.remove(question);
    }
}
