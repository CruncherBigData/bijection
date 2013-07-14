package com.twitter.bijection.jodatime

import org.scalacheck.Properties
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

import com.twitter.bijection.{ Bijection, BaseProperties, ImplicitBijection }
//import Conversion.asMethod
import java.util.Date
import org.joda.time.DateTime
import com.twitter.bijection._

object DateBijectionsLaws extends Properties("DateBijections") with BaseProperties with DateBijections with DateInjections {

  //bijections test
  implicit val Long: Arbitrary[Long] = arbitraryViaFn { (s: DateTime) => (s.getMillis()) }
  property("Joda <=> Long") = isBijection[DateTime, Long]

  implicit val DateTime: Arbitrary[DateTime] = arbitraryViaFn { (s: Date) => (new DateTime(s)) }
  property("Date <=> Joda") = isBijection[Date, DateTime]

  //injection test
  property("round trips Date -> String") = isLooseInjection[Date, String]
  property("round trips Joda -> String") = isLooseInjection[DateTime, String]
  property("round trips Joda -> Date") = isLooseInjection[DateTime, Date]

}