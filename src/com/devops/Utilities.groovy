package com.devops

class Utilities implements Serializable {
  def steps
  Utilities(steps) {this.steps = steps}
  def mvn(args) {
    steps.sh "${steps.tool 'maven 3.6.0'}/bin/mvn -o ${args}"
  }
}
