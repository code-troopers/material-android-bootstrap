## Material Android Bootstrap 

[![Build Status](https://travis-ci.org/code-troopers/material-android-bootstrap.svg?branch=master)](https://travis-ci.org/code-troopers/material-android-bootstrap)

This is a bootstrap to rapidly launch Android projects on API>14 with a material theme

This bootstrap contains :

* material theme with appcompat to API>14
* new toolbar implementation
* a drawer who respect the material guidelines (only on API21)
* Dagger 2
* ButterKnife
* Picasso
* Icepick
* AutoParcel
* Crashlytics
* JUnit 4
* Espresso
* LeakCanary
* Hugo
* Retrolambda

### Travis Notifications
In order to have Travis to notify about the build status on a Slack channel without having to expose credentials in a public repository (see [this link](https://docs.travis-ci.com/user/notifications/#Slack-notifications)),
we just have to use this command based on mgruener/travis-cli docker image from the root of the sources :
`docker run -v $PWD:/home/travis/workspace -w /home/travis/workspace --rm mgruener/travis-cli travis encrypt "<account>:<token>" --add notifications.slack`

## Thanks

This is inspired by this [article](http://antonioleiva.com/material-design-everywhere/), and this [stackoverflow post](http://stackoverflow.com/questions/26745300/navigation-drawer-semi-transparent-over-status-bar-not-working)


