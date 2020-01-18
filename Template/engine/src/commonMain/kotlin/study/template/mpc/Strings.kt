package study.template.mpc

expect fun platformName(): PlatformName

inline class PlatformName(val value: String)
