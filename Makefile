test:
	boot build
	boot -d seancorfield/boot-new new -t boot-react-native -n dummy-app -f
	cd dummy-app/app; npm i
	cd dummy-app; boot dev --platform ios
