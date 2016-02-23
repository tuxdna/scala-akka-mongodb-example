import org.scalatest.FlatSpec

class MainTest extends FlatSpec {

  "sample test " should "run" in {
    println("I am a sample test")
    assert(true)
  }

  it should "contain 11 characters" in {
    assert("Hello world".size == 11)
  }

  it should "start with 'Hello'" in {
    assert("Hello world".startsWith("Hello"))
  }
  it should "end with 'world'" in {
    assert("Hello world".endsWith("world"))
  }


}