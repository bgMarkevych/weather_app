package com.meteoship.model.exception

import java.lang.Exception

class NetworkNotAvailable(val error: String): Exception(error) {
}