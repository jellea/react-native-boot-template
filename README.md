# boot-react-native-template

A Boot template for [`boot-react-native`](https://github.com/mjmeintjes/boot-react-native).
Currently only iOS works.

## Usage

Use the snapshot version from Clojars:

```
boot -d seancorfield/boot-new new -t boot-react-native -n name-of-your-app -S
cd name-of-your-app
boot dev --platform ios
```

Test locally with reload:
```
boot watch build
```

## TODO
- [ ] Use boot-react-native directly from clojars instead of git subtree.
- [ ] Fix Android (need help, never did Android!)

## License

Copyright © 2016 Jelle Akkerman

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
