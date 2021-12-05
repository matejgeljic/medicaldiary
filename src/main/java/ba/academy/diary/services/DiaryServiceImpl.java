package ba.academy.diary.services;

import ba.academy.diary.dto.DiaryInput;
import ba.academy.diary.dto.MedicineType;
import ba.academy.diary.dto.Medicines;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DiaryServiceImpl implements DiaryService {

  List<DiaryInput> myobjects = new ArrayList<>();

  @ConfigProperty(name = "prefix.message")
  String prefix;

  @Override
  public List<DiaryInput> getDiary() {
    return myobjects;
  }

  @Override
  public DiaryInput addDiaryInput(DiaryInput input) {
    Random rand = new Random(); //instance of random class
    input.setDate(new Date());
    if (input.getId() == null) {
      input.setId(rand.nextInt());
    }
    myobjects.add(input);
    return input;
  }

  @Override
  public DiaryInput getSingleDiary(Integer id) {
    DiaryInput singleDiary = new DiaryInput();
    for (DiaryInput singleDiaryById : myobjects) {
      if (singleDiaryById.getId().equals(id)) {
        singleDiary = singleDiaryById;
        break;
      }
    }
    return singleDiary;
  }

  @Override
  public DiaryInput editDiary(Integer id, DiaryInput inputDiary) {
    DiaryInput updatedDiary = new DiaryInput();
    for (DiaryInput oldDiaryById : myobjects) {
      if (oldDiaryById.getId().equals(id)) {
        int index = myobjects.indexOf(oldDiaryById);
        myobjects.set(index, inputDiary);
        updatedDiary = inputDiary;
        break;
      }
    }

    return updatedDiary;
  }

  @Override
  public List<DiaryInput> deleteDiary(Integer id) {
    if (id != null) {
      myobjects.removeIf(existingDiary -> existingDiary.getId().equals(id));
    }

    return myobjects;
  }

}