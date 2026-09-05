package lesson19;

import java.util.Objects;

public class ShortCoin implements Comparable<ShortCoin> {
   private int nominal;

   public ShortCoin(int nominal) {
      this.nominal = nominal;
   }

   public int getNominal() {
      return nominal;
   }

   public void setNominal(int nominal) {
      this.nominal = nominal;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ShortCoin shortCoin = (ShortCoin) o;
      return nominal == shortCoin.nominal;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(nominal);
   }

   @Override
   public String toString() {
      return "ShortCoin{" +
              "nominal=" + nominal +
              '}';
   }

   @Override
   public int compareTo(ShortCoin o) {
      //Сначала отсортируем по номиналу по убыванию
      return o.nominal - this.nominal;

   }
}
