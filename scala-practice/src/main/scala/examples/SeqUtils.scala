package io.wcygan
package examples

import math.MathUtils

object SeqUtils {
  def fiveUpperEvenWords(seq: Seq[String]): Seq[String] = {
    seq.filter(s => s.length % 2 == 0)
      .map(_.toUpperCase)
      .take(5)
  }
}
