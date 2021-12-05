package ba.academy.diary.services;

import ba.academy.diary.dto.DiaryInput;
import java.util.List;

public interface DiaryService {

  List<DiaryInput> getDiary();

  DiaryInput addDiaryInput(DiaryInput input);

  DiaryInput getSingleDiary(Integer id);

  DiaryInput editDiary(Integer id, DiaryInput inputDiary);

  List<DiaryInput> deleteDiary(Integer id);

}
