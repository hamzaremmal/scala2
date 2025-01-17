/*
 * Scala (https://www.scala-lang.org)
 *
 * Copyright EPFL and Lightbend, Inc. dba Akka
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package scala
package reflect
package internal

// Flags at each index of a flags Long.  Those marked with /M are used in
// Parsers/JavaParsers and therefore definitely appear on Modifiers; but the
// absence of /M on the other flags does not imply they aren't.
//
// Generated by mkFlagsTable() at Thu Feb 02 20:31:52 PST 2012
//
//  0:          PROTECTED/M
//  1:           OVERRIDE/M
//  2:            PRIVATE/M
//  3:           ABSTRACT/M
//  4:           DEFERRED/M
//  5:              FINAL/M
//  6:               METHOD
//  7:          INTERFACE/M
//  8:               MODULE
//  9:           IMPLICIT/M
// 10:             SEALED/M
// 11:               CASE/M
// 12:            MUTABLE/M
// 13:              PARAM/M
// 14:              PACKAGE
// 15:              MACRO/M
// 16:        BYNAMEPARAM/M      CAPTURED COVARIANT/M
// 17:      CONTRAVARIANT/M INCONSTRUCTOR       LABEL
// 18:        ABSOVERRIDE/M
// 19:              LOCAL/M
// 20:               JAVA/M
// 21:            SYNTHETIC
// 22:               STABLE
// 23:             STATIC/M
// 24:       CASEACCESSOR/M
// 25:       DEFAULTPARAM/M       TRAIT/M
// 26:               BRIDGE
// 27:             ACCESSOR
// 28:        SUPERACCESSOR
// 29:      PARAMACCESSOR/M
// 30:            MODULEVAR
// 31:               LAZY/M
// 32:             IS_ERROR
// 33:           OVERLOADED
// 34:               LIFTED
// 35:          EXISTENTIAL       MIXEDIN
// 36:         EXPANDEDNAME
// 37:           PRESUPER/M
// 38:           TRANS_FLAG
// 39:               LOCKED
// 40:          SPECIALIZED
// 41:        DEFAULTINIT/M
// 42:              VBRIDGE
// 43:              VARARGS
// 44:         TRIEDCOOKING
// 45:       SYNCHRONIZED/M
// 46:             ARTIFACT
// 47: JAVA_DEFAULTMETHOD/M
// 48:            JAVA_ENUM
// 49:      JAVA_ANNOTATION
// 50:
// 51:         lateDEFERRED
// 52:            lateFINAL
// 53:           lateMETHOD
// 54:        lateINTERFACE
// 55:           lateMODULE
// 56:         notPROTECTED
// 57:          notOVERRIDE
// 58:           notPRIVATE
// 59:
// 60:              SCALA3X
// 61:
// 62:
// 63:

/** Flags set on Modifiers instances in the parsing stage.
 */
class ModifierFlags {
  final val IMPLICIT      = 1L << 9
  final val FINAL         = 1L << 5    // May not be overridden. Note that java final implies much more than scala final.
  final val PRIVATE       = 1L << 2
  final val PROTECTED     = 1L << 0

  final val SEALED        = 1L << 10
  final val OVERRIDE      = 1L << 1
  final val CASE          = 1L << 11
  final val ABSTRACT      = 1L << 3        // abstract class, or used in conjunction with abstract override.
                                          // Note difference to DEFERRED!
  final val DEFERRED      = 1L << 4        // was `abstract` for members | trait is virtual
  final val INTERFACE     = 1L << 7        // symbol is an interface. the flag is set for:
                                          //  - scala-defined traits with only abstract methods or fields
                                          //  - any java-defined interface (even if it has default methods)
  final val MUTABLE       = 1L << 12       // symbol is a mutable variable.
  final val PARAM         = 1L << 13       // symbol is a (value or type) parameter to a method
  final val MACRO         = 1L << 15       // symbol is a macro definition

  final val COVARIANT     = 1L << 16       // symbol is a covariant type variable
  final val BYNAMEPARAM   = 1L << 16       // parameter is by name
  final val CONTRAVARIANT = 1L << 17       // symbol is a contravariant type variable
  final val ABSOVERRIDE   = 1L << 18       // combination of abstract & override
  final val LOCAL         = 1L << 19       // symbol is local to current class (i.e. private[this] or protected[this]
                                          // pre: PRIVATE or PROTECTED are also set
  final val JAVA          = 1L << 20       // symbol was defined by a Java class
  final val SCALA3X       = 1L << 60       // class was defined in Scala 3
  final val STATIC        = 1L << 23       // static field, method or class
  final val CASEACCESSOR  = 1L << 24       // symbol is a case parameter (or its accessor, or a GADT skolem)
  final val TRAIT         = 1L << 25       // symbol is a trait
  final val DEFAULTPARAM  = 1L << 25       // the parameter has a default value
  final val PARAMACCESSOR = 1L << 29       // for field definitions generated for primary constructor
                                          //   parameters (no matter if it's a 'val' parameter or not)
                                          // for parameters of a primary constructor ('val' or not)
                                          // for the accessor methods generated for 'val' or 'var' parameters
  final val LAZY          = 1L << 31      // symbol is a lazy val. can't have MUTABLE unless transformed by typer
  final val PRESUPER      = 1L << 37      // value is evaluated before super call
  final val DEFAULTINIT   = 1L << 41      // symbol is initialized to the default value: used by -Xcheckinit
  final val ARTIFACT      = 1L << 46      // symbol should be ignored when typechecking; will be marked ACC_SYNTHETIC in bytecode
                                          // to see which symbols are marked as ARTIFACT, see scaladocs for FlagValues.ARTIFACT
  final val JAVA_DEFAULTMETHOD = 1L << 47     // symbol is a java default method
  final val JAVA_ENUM          = 1L << 48     // symbol is a java enum
  final val JAVA_ANNOTATION    = 1L << 49     // symbol is a java annotation

  // Overridden.
  def flagToString(flag: Long): String = ""

  final val PrivateLocal   = PRIVATE | LOCAL
  final val ProtectedLocal = PROTECTED | LOCAL
  final val AccessFlags    = PRIVATE | PROTECTED | LOCAL
}
object ModifierFlags extends ModifierFlags

/** All flags and associated operations */
class Flags extends ModifierFlags {
  final val METHOD        = 1L << 6        // a method
  final val MODULE        = 1L << 8        // symbol is module or class implementing a module
  final val PACKAGE       = 1L << 14       // symbol is a java package

  final val CAPTURED      = 1L << 16       // variable is accessed from nested function.  Set by LambdaLift.
  final val LABEL         = 1L << 17       // method symbol is a label. Set by TailCall
  final val INCONSTRUCTOR = 1L << 17       // class symbol is defined in this/superclass constructor.
  final val SYNTHETIC     = 1L << 21       // symbol is compiler-generated (compare with ARTIFACT)
  final val STABLE        = 1L << 22       // functions that are assumed to be stable
                                          // (typically, access methods for valdefs)
                                          // or classes that do not contain abstract types.
  final val BRIDGE        = 1L << 26       // function is a bridge method. Set by Erasure
  final val ACCESSOR      = 1L << 27       // a value or variable accessor (getter or setter)

  final val SUPERACCESSOR = 1L << 28       // a super accessor
  final val MODULEVAR     = 1L << 30       // for variables: is the variable caching a module value

  final val IS_ERROR      = 1L << 32      // symbol is an error symbol
  final val OVERLOADED    = 1L << 33      // symbol is overloaded
  final val LIFTED        = 1L << 34      // class has been lifted out to package level
                                          // local value has been lifted out to class level
                                          // todo: make LIFTED = latePRIVATE?
  final val MIXEDIN       = 1L << 35      // term member has been mixed in
  final val EXISTENTIAL   = 1L << 35      // type is an existential parameter or skolem
  final val EXPANDEDNAME  = 1L << 36      // name has been expanded with class suffix
  final val TRANS_FLAG    = 1L << 38      // transient flag guaranteed to be reset after each phase.

  final val LOCKED        = 1L << 39      // temporary flag to catch cyclic dependencies
  final val SPECIALIZED   = 1L << 40      // symbol is a generated specialized member
  final val VBRIDGE       = 1L << 42      // symbol is a varargs bridge (but not a bridge at the bytecode level)

  final val VARARGS       = 1L << 43      // symbol is a Java-style varargs method
  final val TRIEDCOOKING  = 1L << 44      // `Cooking` has been tried on this symbol
                                          // A Java method's type is `cooked` by transforming raw types to existentials

  final val SYNCHRONIZED  = 1L << 45      // symbol is a method which should be marked ACC_SYNCHRONIZED

  final val SYNTHESIZE_IMPL_IN_SUBCLASS = 1L << 50 // used in fields phase to indicate this accessor should receive an implementation in a subclass

  // flags used strictly internally in the Fields phase (info/tree transform):
  final val NEEDS_TREES   = 1L << 59           // this symbol needs a tree. (distinct from SYNTHESIZE_IMPL_IN_SUBCLASS)

  // ------- shift definitions -------------------------------------------------------
  //
  // Flags from 1L to (1L << 50) are normal flags.
  //
  // The "late" counterpart to flags DEFERRED (1L << 4) to MODULE (1L << 8)
  // show up in `sym.flags` as their regular counterpart once the phase mask admits them (see below).
  // The first late flag (lateDEFERRED) is at (1L << 51), i.e., late flags are shifted by 47. The last one is (1L << 55).
  // Think of it as a poor man's flag history akin to the type history for a symbol's info.
  //
  // The "not" counterpart to flags PROTECTED (1L) to PRIVATE (1L << 2)
  // are negated flags that suppress their counterpart after a specific phase (see below).
  // They are shifted by 56, i.e., the first negated flag (notPROTECTED) is at (1L << 56), the last at (1L << 58).
  //
  // Late and negative flags are only enabled after certain phases, implemented by the phaseNewFlags
  // method of the SubComponent, so they implement a bit of a flag history.
  //
  // The flags (1L << 59) to (1L << 63) are currently unused. If added to the InitialFlags mask,
  // they could be used as normal flags.

  final val InitialFlags  = 0x1007FFFFFFFFFFFFL // normal flags, enabled from the first phase: 1L to (1L << 50) + (1L << 60)
  final val LateFlags     = 0x00F8000000000000L // flags that override flags in (1L << 4) to (1L << 8): DEFERRED, FINAL, INTERFACE, METHOD, MODULE
  final val AntiFlags     = 0x0700000000000000L // flags that cancel flags in 1L to (1L << 2): PROTECTED, OVERRIDE, PRIVATE
  final val LateShift     = 47
  final val AntiShift     = 56

  /** all of the flags that are unaffected by phase */
  final val PhaseIndependentFlags = 0xF807FFFFFFFFFE08L
  //this should be
  // final val PhaseIndependentFlags = (-1L & ~LateFlags & ~AntiFlags & ~(LateFlags >>> LateShift) & ~(AntiFlags >>> AntiShift)))
  // but the constant folder does not optimise this! Good news is that is expected to be fixed soon :-)
  assert (PhaseIndependentFlags == (-1L & ~LateFlags & ~AntiFlags & ~(LateFlags >>> LateShift) & ~(AntiFlags >>> AntiShift)))


  // Flags which sketchily share the same slot
  // 16:   BYNAMEPARAM/M      CAPTURED COVARIANT/M
  // 17: CONTRAVARIANT/M INCONSTRUCTOR       LABEL
  // 25:  DEFAULTPARAM/M       TRAIT/M
  // 35:     EXISTENTIAL       MIXEDIN
  final val OverloadedFlagsMask = 0L | BYNAMEPARAM | CONTRAVARIANT | DEFAULTPARAM | EXISTENTIAL

  // ------- late flags (set by a transformer phase) ---------------------------------
  //
  // Summary of when these are claimed to be first used.
  // You can get this output with scalac -Vphases -Vdebug.
  //
  //     refchecks   7  [START] <latemethod>
  //    specialize  13  [START] <latefinal> <notprivate>
  // explicitouter  14  [START] <notprotected>
  //       erasure  15  [START] <latedeferred>
  //         mixin  20  [START] <latemodule> <notoverride>
  //
  // notPRIVATE set in Symbols#makeNotPrivate, IExplicitOuter#transform, Inliners.
  // notPROTECTED set in ExplicitOuter#transform.

//  final val lateDEFERRED  = (0L + DEFERRED) << LateShift // unused
//  final val lateFINAL     = (0L + FINAL) << LateShift    // only used for inliner -- could be subsumed by notPRIVATE?
//  final val lateMETHOD    = (0L + METHOD) << LateShift   // unused
//  final val lateMODULE    = (0L + MODULE) << LateShift   // unused

//  final val notOVERRIDE   = (0L + OVERRIDE) << AntiShift // unused
  final val notPRIVATE    = (0L + PRIVATE) << AntiShift
  final val notPROTECTED  = (0L + PROTECTED) << AntiShift

  // ------- masks -----------------------------------------------------------------------

  /** To be a little clearer to people who aren't habitual bit twiddlers.
   */
  final val AllFlags = -1L

  // TODO - there's no call to slap four flags onto every package.
  final val PackageFlags = MODULE | PACKAGE | FINAL | JAVA

  // FINAL not included here due to possibility of object overriding.
  // In fact, FINAL should not be attached regardless.  We should be able
  // to reconstruct whether an object was marked final in source.
  final val ModuleFlags = MODULE

  /** These modifiers can be set explicitly in source programs.  This is
   *  used only as the basis for the default flag mask (which ones to display
   *  when printing a normal message.)
   */
  final val ExplicitFlags =
    PRIVATE | PROTECTED | ABSTRACT | FINAL | SEALED |
    OVERRIDE | CASE | IMPLICIT | ABSOVERRIDE | LAZY | JAVA_DEFAULTMETHOD

  /** The two bridge flags */
  final val BridgeFlags = BRIDGE | VBRIDGE
  final val BridgeAndPrivateFlags = BridgeFlags | PRIVATE

  /** These modifiers appear in TreePrinter output. */
  final val PrintableFlags =
    ExplicitFlags | BridgeFlags | LOCAL | SYNTHETIC | STABLE | CASEACCESSOR | MACRO |
    ACCESSOR | SUPERACCESSOR | PARAMACCESSOR | STATIC | SPECIALIZED | SYNCHRONIZED | ARTIFACT |
    SYNTHESIZE_IMPL_IN_SUBCLASS | NEEDS_TREES

  /** When a symbol for a field is created, only these flags survive
   *  from Modifiers.  Others which may be applied at creation time are:
   *  PRIVATE, LOCAL.
   */
  final val FieldFlags =
    MUTABLE | CASEACCESSOR | PARAMACCESSOR | STATIC | FINAL | PRESUPER | LAZY | DEFAULTINIT

  /** Masks for getters and setters, where the flags are derived from those
   *  on the field's modifiers.  Both getters and setters get the ACCESSOR flag.
   *  Getters of immutable values also get STABLE.
   */
  final val GetterFlags = ~(PRESUPER | MUTABLE)
  final val SetterFlags = ~(PRESUPER | MUTABLE | STABLE | CASEACCESSOR | IMPLICIT)

  /** Since DEFAULTPARAM is overloaded with TRAIT, we need some additional
   *  means of determining what that bit means. Usually DEFAULTPARAM is coupled
   *  with PARAM, which suffices. Default getters get METHOD instead.
   *  This constant is the mask of flags which can survive from the parameter modifiers.
   *  See paramFlagsToDefaultGetter for the full logic.
   */
  final val DefaultGetterFlags = PRIVATE | PROTECTED | FINAL | PARAMACCESSOR

  /** When a symbol for a method parameter is created, only these flags survive
   *  from Modifiers.  Others which may be applied at creation time are:
   *  SYNTHETIC.
   */
  final val ValueParameterFlags = BYNAMEPARAM | IMPLICIT | DEFAULTPARAM | STABLE | SYNTHETIC
  final val BeanPropertyFlags   = DEFERRED | OVERRIDE | STATIC
  final val VarianceFlags       = COVARIANT | CONTRAVARIANT

  /** These appear to be flags which should be transferred from owner symbol
   *  to a newly created constructor symbol.
   */
  final val ConstrFlags = JAVA

  /** Module flags inherited by their module-class */
  final val ModuleToClassFlags = AccessFlags | PackageFlags | CASE | SYNTHETIC

  final val ValidAliasFlags = SUPERACCESSOR | PARAMACCESSOR | MIXEDIN | SPECIALIZED


  /** These flags are not pickled */
  final val FlagsNotPickled = IS_ERROR | OVERLOADED | LIFTED | TRANS_FLAG | LOCKED | TRIEDCOOKING | SCALA3X

  // A precaution against future additions to FlagsNotPickled turning out
  // to be overloaded flags thus not-pickling more than intended.
  assert(
    (OverloadedFlagsMask & FlagsNotPickled) == 0,
    "overloaded flags should not overlap with FlagsNotPickled; found: " +
      flagsToString(OverloadedFlagsMask & FlagsNotPickled)
  )

  /** These flags are pickled */
  final val PickledFlags  = (
      (InitialFlags & ~FlagsNotPickled)
    | notPRIVATE // for value class constructors (scala/bug#6601), and private members referenced
                 // in @inline-marked methods publicized in SuperAccessors (see scala/bug#6608, e6b4204604)
  )

  /** If we have a top-level class or module
   *  and someone asks us for a flag not in TopLevelPickledFlags,
   *  then we don't need unpickling to give a definite answer.
   */
  final val TopLevelPickledFlags = PickledFlags & ~(MODULE | METHOD | PACKAGE | PARAM | EXISTENTIAL)

  def paramFlagsToDefaultGetter(paramFlags: Long): Long =
    (paramFlags & DefaultGetterFlags) | SYNTHETIC | METHOD | DEFAULTPARAM

  def getterFlags(fieldFlags: Long): Long = ACCESSOR + (
    if ((fieldFlags & MUTABLE) != 0) fieldFlags & ~MUTABLE & ~PRESUPER
    else fieldFlags & ~PRESUPER | STABLE
  )

  def setterFlags(fieldFlags: Long): Long =
    getterFlags(fieldFlags) & ~STABLE & ~CASEACCESSOR

 // ------- pickling and unpickling of flags -----------------------------------------------

  // The flags from 0x001 to 0x800 are different in the raw flags
  // and in the pickled format.

  private final val IMPLICIT_PKL   = (1L << 0)
  private final val FINAL_PKL      = (1L << 1)
  private final val PRIVATE_PKL    = (1L << 2)
  private final val PROTECTED_PKL  = (1L << 3)
  private final val SEALED_PKL     = (1L << 4)
  private final val OVERRIDE_PKL   = (1L << 5)
  private final val CASE_PKL       = (1L << 6)
  private final val ABSTRACT_PKL   = (1L << 7)
  private final val DEFERRED_PKL   = (1L << 8)
  private final val METHOD_PKL     = (1L << 9)
  private final val MODULE_PKL     = (1L << 10)
  private final val INTERFACE_PKL  = (1L << 11)

  private final val PKL_MASK       = 0x00000FFF

  /** Pickler correspondence, ordered roughly by frequency of occurrence */
  private def rawPickledCorrespondence = Array[(Long, Long)](
    (METHOD, METHOD_PKL),
    (PRIVATE, PRIVATE_PKL),
    (FINAL, FINAL_PKL),
    (PROTECTED, PROTECTED_PKL),
    (CASE, CASE_PKL),
    (DEFERRED, DEFERRED_PKL),
    (MODULE, MODULE_PKL),
    (OVERRIDE, OVERRIDE_PKL),
    (INTERFACE, INTERFACE_PKL),
    (IMPLICIT, IMPLICIT_PKL),
    (SEALED, SEALED_PKL),
    (ABSTRACT, ABSTRACT_PKL)
  )

  private[this] val mappedRawFlags = rawPickledCorrespondence map (_._1)
  private[this] val mappedPickledFlags = rawPickledCorrespondence map (_._2)

  private class MapFlags(from: Array[Long], to: Array[Long]) extends (Long => Long) {
    val fromSet = from.foldLeft(0L) (_ | _)

    def apply(flags: Long): Long = {
      var result = flags & ~fromSet
      var tobeMapped = flags & fromSet
      var i = 0
      while (tobeMapped != 0) {
        if ((tobeMapped & from(i)) != 0) {
          result |= to(i)
          tobeMapped &= ~from(i)
        }
        i += 1
      }
      result
    }
  }

  val rawToPickledFlags: Long => Long = new MapFlags(mappedRawFlags, mappedPickledFlags)
  val pickledToRawFlags: Long => Long = new MapFlags(mappedPickledFlags, mappedRawFlags)

  // ------ displaying flags --------------------------------------------------------

  // Generated by mkFlagToStringMethod() at Thu Feb 02 20:31:52 PST 2012
  @annotation.switch override def flagToString(flag: Long): String = flag match {
    case           PROTECTED => "protected"                           // (1L << 0)
    case            OVERRIDE => "override"                            // (1L << 1)
    case             PRIVATE => "private"                             // (1L << 2)
    case            ABSTRACT => "abstract"                            // (1L << 3)
    case            DEFERRED => "<deferred>"                          // (1L << 4)
    case               FINAL => "final"                               // (1L << 5)
    case              METHOD => "<method>"                            // (1L << 6)
    case           INTERFACE => "<interface>"                         // (1L << 7)
    case              MODULE => "<module>"                            // (1L << 8)
    case            IMPLICIT => "implicit"                            // (1L << 9)
    case              SEALED => "sealed"                              // (1L << 10)
    case                CASE => "case"                                // (1L << 11)
    case             MUTABLE => "<mutable>"                           // (1L << 12)
    case               PARAM => "<param>"                             // (1L << 13)
    case             PACKAGE => "<package>"                           // (1L << 14)
    case               MACRO => "<macro>"                             // (1L << 15)
    case         BYNAMEPARAM => "<bynameparam/captured/covariant>"    // (1L << 16)
    case       CONTRAVARIANT => "<contravariant/inconstructor/label>" // (1L << 17)
    case         ABSOVERRIDE => "abstract override"                   // (1L << 18)
    case               LOCAL => "<local>"                             // (1L << 19)
    case                JAVA => "<java>"                              // (1L << 20)
    case           SYNTHETIC => "<synthetic>"                         // (1L << 21)
    case              STABLE => "<stable>"                            // (1L << 22)
    case              STATIC => "<static>"                            // (1L << 23)
    case        CASEACCESSOR => "<caseaccessor>"                      // (1L << 24)
    case        DEFAULTPARAM => "<defaultparam/trait>"                // (1L << 25)
    case              BRIDGE => "<bridge>"                            // (1L << 26)
    case            ACCESSOR => "<accessor>"                          // (1L << 27)
    case       SUPERACCESSOR => "<superaccessor>"                     // (1L << 28)
    case       PARAMACCESSOR => "<paramaccessor>"                     // (1L << 29)
    case           MODULEVAR => "<modulevar>"                         // (1L << 30)
    case                LAZY => "lazy"                                // (1L << 31)
    case            IS_ERROR => "<is_error>"                          // (1L << 32)
    case          OVERLOADED => "<overloaded>"                        // (1L << 33)
    case              LIFTED => "<lifted>"                            // (1L << 34)
    case         EXISTENTIAL => "<existential/mixedin>"               // (1L << 35)
    case        EXPANDEDNAME => "<expandedname>"                      // (1L << 36)
    case            PRESUPER => "<presuper>"                          // (1L << 37)
    case          TRANS_FLAG => "<trans_flag>"                        // (1L << 38)
    case              LOCKED => "<locked>"                            // (1L << 39)
    case         SPECIALIZED => "<specialized>"                       // (1L << 40)
    case         DEFAULTINIT => "<defaultinit>"                       // (1L << 41)
    case             VBRIDGE => "<vbridge>"                           // (1L << 42)
    case             VARARGS => "<varargs>"                           // (1L << 43)
    case        TRIEDCOOKING => "<triedcooking>"                      // (1L << 44)
    case        SYNCHRONIZED => "<synchronized>"                      // (1L << 45)
    case            ARTIFACT => "<artifact>"                          // (1L << 46)
    case  JAVA_DEFAULTMETHOD => "<defaultmethod>"                     // (1L << 47)
    case           JAVA_ENUM => "<enum>"                              // (1L << 48)
    case     JAVA_ANNOTATION => "<annotation>"                        // (1L << 49)
    case SYNTHESIZE_IMPL_IN_SUBCLASS => "<sub_synth>"                 // (1L << 50)
    case   0x08000000000000L => "<latedeferred>"                      // (1L << 51)
    case   0x10000000000000L => "<latefinal>"                         // (1L << 52)
    case   0x20000000000000L => "<latemethod>"                        // (1L << 53)
    case   0x40000000000000L => ""                                    // (1L << 54)
    case   0x80000000000000L => "<latemodule>"                        // (1L << 55)
    case      `notPROTECTED` => "<notprotected>"                      // (1L << 56)
    case  0x200000000000000L => "<notoverride>"                       // (1L << 57)
    case        `notPRIVATE` => "<notprivate>"                        // (1L << 58)
    case         NEEDS_TREES => "<needs_trees>"                       // (1L << 59)
    case             SCALA3X => "<scala3>"                            // (1L << 60)
    case 0x2000000000000000L => ""                                    // (1L << 61)
    case 0x4000000000000000L => ""                                    // (1L << 62)
    case 0x8000000000000000L => ""                                    // (1L << 63)
    case _ => ""
  }

  private def accessString(flags: Long, privateWithin: String)= (
    if (privateWithin == "") {
      if ((flags & PrivateLocal) == PrivateLocal) "private[this]"
      else if ((flags & ProtectedLocal) == ProtectedLocal) "protected[this]"
      else if ((flags & PRIVATE) != 0) "private"
      else if ((flags & PROTECTED) != 0) "protected"
      else ""
    }
    else if ((flags & PROTECTED) != 0) "protected[" + privateWithin + "]"
    else "private[" + privateWithin + "]"
  )

  // FIXME: This method is used several places internally. Remove its
  //        internal use and then re-deprecate it.
  // @deprecated("use flagString on the flag-carrying member", "2.10.0")
  private[scala] def flagsToString(flags: Long, privateWithin: String): String = {
    val access    = accessString(flags, privateWithin)
    val nonAccess = flagsToString(flags & ~AccessFlags)

    List(nonAccess, access) filterNot (_ == "") mkString " "
  }

  // FIXME: This method is used several places internally. Remove its
  //        internal use and then re-deprecate it.
  // @deprecated("use flagString on the flag-carrying member", "2.10.0")
  private[scala] def flagsToString(flags: Long): String = {
    // Fast path for common case
    if (flags == 0L) "" else {
      var sb: StringBuilder = null
      var i = 0
      while (i <= MaxBitPosition) {
        val mask = rawFlagPickledOrder(i)
        if ((flags & mask) != 0L) {
          val s = flagToString(mask)
          if (s.length > 0) {
            if (sb eq null) sb = new StringBuilder
            else if (!sb.isEmpty) sb.append(" ")
            sb.append(s)
          }
        }
        i += 1
      }
      if (sb eq null) "" else sb.toString
    }
  }

  // List of the raw flags, in pickled order
  final val MaxBitPosition = 62

  final val pickledListOrder: List[Long] = {
    val all   = 0 to MaxBitPosition map (1L << _)
    val front = mappedRawFlags map (_.toLong)

    front.toList ++ (all filterNot (front contains _))
  }
  final val rawFlagPickledOrder: Array[Long] = pickledListOrder.toArray
}

object Flags extends Flags
