package boardgame;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {
  private int row;
  private int column;

  @Override
  public String toString() {
    return String.format("Row: %d | Col: %d", row, column);
  }
}
