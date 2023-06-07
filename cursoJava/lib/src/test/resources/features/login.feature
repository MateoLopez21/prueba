Feature: Loguear en el sistema

  @Disabled
  Scenario: Loguear sin credenciales
    Given Un usuario que ingresa al login
    When El usuario no ingresa ninguna credencial
    Then La pagina muestra un mensaje de error indicando que el usuario esta vacio

  @Disabled
  Scenario: Loguear con credenciales invalidas
    Given Un usuario que ingresa al login
    When El usuario ingresa credenciales invalidas
    Then La pagina le muestra un mensaje de error indicando que las credenciales son invalidas

  Scenario: Loguear con credenciales validas
    Given Un usuario que ingresa al login
    When El usuario ingresa credenciales validas
    Then La pagina valida credenciales e ingresa a la pagina principal
