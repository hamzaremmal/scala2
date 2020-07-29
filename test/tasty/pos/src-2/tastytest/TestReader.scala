package tastytest

/**
 * suspended from run test until we fix https://github.com/lampepfl/dotty/issues/7328
 */
object TestReader extends Suite("TestReader") {

  implicit def mkReaderMonad[Ctx]: Reader[Ctx] = new Reader[Ctx]() {}

  def pureToString[F[_], A](fa: F[A])(implicit F: Monad[F]): F[String] = F.extension_flatMap(fa)(a => F.pure(a.toString))

  test {
    val f = pureToString((s: Unit) => 101)
    assert(f(()) === "101")
  }
}
