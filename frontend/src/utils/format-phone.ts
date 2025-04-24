export const formatPhone = (phone: string): string => {
    const digits = phone.replace(/\D/g, '')

    if (digits.length !== 11 || !digits.startsWith('7')) {
        return phone 
    }

    const part1 = digits.slice(1, 4)
    const part2 = digits.slice(4, 7)
    const part3 = digits.slice(7, 9)
    const part4 = digits.slice(9, 11)

    return `+7(${part1}) ${part2} ${part3} ${part4}`
}
