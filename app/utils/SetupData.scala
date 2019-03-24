package utils

object SetupData {

  val welcomeTemplate: String =
    """
      |Hello dear {{user.salutation}} {{user.name}},
      |
      |we are very happy to welcome you to our newsletter.
      |
      |In case you don't want to receive any further newsletters in the future please unsubscribe here:
      |https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}}
      |
      |Best Regards,
      |Your Customer Support Team
    """.stripMargin

  val newsletterTemplate: String =
    """
      |Hello dear {{user.salutation}} {{user.name}},
      |
      |this are our latest news...
      |
      |In case you don't want to receive any further newsletters in the future please unsubscribe here:
      |https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}}
      |
      |Best Regards,
      |Your Customer Support Team
    """.stripMargin
}
