
: change to eclipse output
  cd ..\bin

: copy base classes
  xcopy /Y /S ..\..\LibGdxJumpAndRun\bin .

: package jar
  jar cvMf JumpAndRun_unsigned.jar de/ data/ META-INF/

: change back to dist dir and copy packaged jar
  cd ..\dist
  move ..\bin\JumpAndRun_unsigned.jar JumpAndRun_unsigned.jar

: sign
  jarsigner -keystore E:\eclipse_workspace\LibGdxJumpAndRun\trunk\LibGdxJumpAndRun-desktop\dist\keystore.jks -storepass chrisy -keypass chrisy -signedjar JumpAndRun.jar JumpAndRun_unsigned.jar Shooter

: delete unsigned
  del JumpAndRun_unsigned.jar

: paws
  pause


: HOW TO create a keystore before signing
: keytool -genkey -alias Shooter -keyalg RSA -keystore E:\eclipse_workspace\Shooter\trunk\dist\keystore.jks -keysize 2048 -validity 100000
